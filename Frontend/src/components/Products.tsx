//TODO: Implement a products page that is only accessible to authenticated users with the Authority "CAN_RETRIEVE_PRODUCTS"
import React, {useEffect} from "react";
import {Card} from "react-bootstrap";
import AxiosUtility from "../utility/AxiosUtility";
import {AxiosInstance} from "axios";
import "../style.css";

interface Product {
    id: string;
    name: string;
    description: string;
    country: string;
    tpe: string;
    price: number;
    dateOfHarvest: string;
}
const Products = () => {
    const api: AxiosInstance = AxiosUtility.getApi()
    const [products, setProducts] = React.useState([]);
    const [loading, setLoading] = React.useState(true);
    const [error, setError] = React.useState(null);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await api.get("/products", {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem('token')}`,
                        'Content-Type': 'application/json',
                    },
                });

                if (response.status !== 200) {
                    new Error("Request not sent");
                }
                const json = await response.data;
                console.log("json", json);
                setProducts(json);


            } catch (error: any) {
                setError(error);

            } finally {
                setLoading(false);
            }
        };
        fetchProducts().then(r => console.log("Products loaded"));

    }, []);
    // Inside your Products component
// ...

    return (
        <div className="cards-container">
            <h1>Authenticated - Products</h1>
            {loading && <p>Loading...</p>}
            {error && <p>Something went wrong: {error}</p>}

            <div className="card-grid">
                {products.map((product: Product) => (
                    <div key={product.id} className="card">
                        <div className="card-content">
                            <h2 className="card-title">{product.name}</h2>
                            <p className="card-subtitle">{product.country}</p>
                            <p className="card-description">{product.description}</p>
                            <p className="card-price">Price per 100g: {product.price}Fr.</p>
                            <p className="card-date">{product.dateOfHarvest}</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Products;