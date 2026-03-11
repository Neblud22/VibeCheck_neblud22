import React, { useEffect } from "react";
import {Card, CardContent, CardMedia, Typography, Button, Grid} from "@mui/material";
import { Link } from "react-router-dom";
import {useEventStore} from "../store/useEventStore.ts";

const ArtistOverview: React.FC = () => {
    const { artists, fetchAllArtists, loading, error } = useEventStore();

    useEffect(() => {
        fetchAllArtists();
    }, []);

    if (loading) return <Typography>Loading artists...</Typography>;
    if (error) return <Typography color="error">{error}</Typography>;

    return (
        <Grid container spacing={{ xs: 1, md: 3 }}>
            {artists.map((artist) => (
                <Card
                    key={artist.id}
                    sx={{ maxWidth: 500, display: "flex", flexDirection: "column" }}
                >
                    <CardMedia
                        component="img"
                        height={200}
                        image={artist.imageUrl}
                        alt={`${artist.firstName} ${artist.lastName}`}
                        sx={{ objectFit: "cover" }}
                    />

                    <CardContent sx={{ flexGrow: 1 }}>
                        <Typography gutterBottom variant="h6">
                            {artist.firstName} {artist.lastName}
                        </Typography>

                        <Typography variant="body2">
                            {artist.description}
                        </Typography>
                    </CardContent>

                    <Button
                        component={Link}
                        to={`/events/artist/${artist.id}`}
                        variant="contained"
                        sx={{ backgroundColor: "#000000", color: "white", m: 1 }}
                    >
                        View Events
                    </Button>
                </Card>
            ))}

        </Grid>
    );
};

export default ArtistOverview;