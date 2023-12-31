import {Navigate, Outlet} from 'react-router-dom';
import {useAuth} from "../contexts/authenticationcontext/AuthenticationContext";
import Authority from "../models/Authority";

export interface ProtectedProps {
  authoritiesToGrantAccess: Authority["name"][];
}

export const Protected = ({authoritiesToGrantAccess}: ProtectedProps) => {
  const {principal, hasAnyAuthority} = useAuth();
  console.log("Principal:", principal);
  console.log("Authorities To Grant Access:", authoritiesToGrantAccess);
  console.log("Has Any Authority:", hasAnyAuthority(authoritiesToGrantAccess));
  return principal && hasAnyAuthority(authoritiesToGrantAccess) ? <Outlet/> : <Navigate to={"/login"}/>
};