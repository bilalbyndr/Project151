import React, {createContext, ReactNode, useContext, useEffect, useReducer, useState} from "react";
import User from "../../models/User";
import {Navigate, redirect} from "react-router-dom";
import axios, {AxiosInstance} from "axios";
import AxiosUtility from "../../utility/AxiosUtility";
import Authority from "../../models/Authority";

interface AuthenticationContextProps {
  principal: User | undefined;
  hasAnyAuthority: (authorities: Authority["name"][]) => boolean;
  logout: () => Promise<void>;
}

enum ActionTypes {
  LOADING,
  FAILED,
  AUTHENTICATED
}

export const AuthenticationContext = createContext<AuthenticationContextProps>(
    {} as AuthenticationContextProps
);

export const useAuth = () => {
  return useContext(AuthenticationContext);
};

export interface AuthenticationContextProviderProps {
  children: ReactNode;
}

const AuthenticationContextProvider = ({children}: AuthenticationContextProviderProps) => {
  const api: AxiosInstance = AxiosUtility.getApi()
  const [principal, setPrincipal] = useState<User | undefined>();
  const [password] = useState("password");
  const reducer = (state: ActionTypes, action: ActionTypes) => {
    switch (action) {
      case ActionTypes.LOADING:
        return ActionTypes.LOADING;
      case ActionTypes.FAILED:
        return ActionTypes.FAILED;
      case ActionTypes.AUTHENTICATED:
        return ActionTypes.AUTHENTICATED;
    }
  }
  const [state, dispatch] = useReducer(reducer, ActionTypes.LOADING)

  useEffect(() => {
  const authenticate = async () => {
    try {
      const loginResponse = await api.post('/users/login', {"email":"max@mustermann.com" ,"password": password});
      if (loginResponse.headers.hasAuthorization) {
        //@ts-ignore
        const token = loginResponse.headers.getAuthorization();
        console.log("Auth.token:", token);

        localStorage.setItem('token', token);

        //TODO: call backend to get principal (e.g through endpoint /users/profile) and pass it to setPrincipal(). The current Max Mustermann is just a mock!
        const response = await api.get('/users/profile', {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`,
          },
        });
        const user: User = response.data;

        if(response) {
          const newUser: User = {
            id: user.id,
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            roles: user.roles,
          };
          await setPrincipal(newUser);
        }

        dispatch(ActionTypes.AUTHENTICATED)
      } else {
        dispatch(ActionTypes.FAILED)
      }
      redirect("/products")
    } catch {
      dispatch(ActionTypes.FAILED)
    }
  }

  authenticate().then(r => console.log("Authenticated"));

  }, [api, password]);


  useEffect(() => {
    hasAnyAuthority(["CAN_RETRIEVE_PRODUCTS"]);
  }, [principal]);

  //TODO: implement hasAnyAuthority() method. Check if principal has any of the authorities passed as parameter
  const hasAnyAuthority = (authorities: Authority["name"][]): boolean => {
    console.log("hasAnyAuthority");

    console.log(principal)
    if(principal) {
      console.log("benRollen");
      console.log("berechtingungen");

      return principal.roles.some(role => {
        if (role.authorities) {
          return role.authorities.some(authority => authorities.includes(authority.name));
        }
        return false;
      });
    }

    return false;
  };

  const logout = async () => {
    setPrincipal(undefined);
    redirect("/login")
  }

  const renderContent = () => {
    switch (state) {
      case ActionTypes.LOADING:
        return <p>LOADING...</p>;

      case ActionTypes.FAILED:
        return <Navigate to={"/login"}/>

      case ActionTypes.AUTHENTICATED:
        return children;
    }
  }

  return (
      <AuthenticationContext.Provider
          value={{
            principal,
            hasAnyAuthority,
            logout
          }}
      >
        {renderContent()}
      </AuthenticationContext.Provider>
  );
};

export default AuthenticationContextProvider;