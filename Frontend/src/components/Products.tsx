//TODO: Implement a products page that is only accessible to authenticated users with the Authority "CAN_RETRIEVE_PRODUCTS"
import React, {useEffect} from "react";
import {Card} from "react-bootstrap";
import {Simulate} from "react-dom/test-utils";
import load = Simulate.load;

interface Product {
    id: string;
    name: string;
    description: string;
    country: string;
    type: string;
    price: number;
    dateOfHarvest: string;
}
const Products = () => {
    const [products, setProducts] = React.useState([]);
    const [loading, setLoading] = React.useState(true);
    const [error, setError] = React.useState(null);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await fetch("/products");
                if (!response.ok) {
                    new Error("Loading error");
                }
                const json = await response.json();
                setProducts(json);

            } catch (error: any) {
                setError(error);

            } finally {
                setLoading(false);
            }
        };
        fetchProducts()
            .then(r => console.log("Products loaded"));

    }, []);

    return (
        <div className={"cards"}>
            <h1>Authenticated - Products</h1>
            {loading && <p>Loading...</p>}
            {error && <p>Something went wrong: {error}</p>}

            {products.map((product: Product) => (
                <Card key={product.id} style={{ width: '18rem' }}>
                    <Card.Body>
                        <Card.Title>{product.name}</Card.Title>
                        <Card.Subtitle className="mb-2 text-muted">{product.country}</Card.Subtitle>
                        <Card.Text>
                            {product.description}
                        </Card.Text>
                        <Card.Text>
                            {product.price}Fr.
                        </Card.Text>
                        <Card.Text>
                            {product.dateOfHarvest}
                        </Card.Text>
                    </Card.Body>
                </Card>
            ))}
        </div>
    )
}

export default Products;