import React, {useEffect, useState} from "react";
import {Card, CardMedia, CardContent, Typography, CardActions, Button, Toolbar, Grid, FormControl, InputLabel, Select, MenuItem, Stack} from "@mui/material";
import { Link } from "react-router-dom";
import RatingDialog from "./RatingDialog.tsx";
import { useEventStore } from "../store/useEventStore";

const EventOverview: React.FC = () => {
    const { events, calcAvg, loading, error, page, orderBy, addPage, minusPage } = useEventStore();
    const [selectedEventId, setSelectedEventId] = useState<number | null>(null);
    const [openRating, setOpenRating] = useState(false);
    const [sortBy, setSortBy] = useState<string>("date")

    const handleOpenRating = (eventId: number) => {
        setSelectedEventId(eventId);
        setOpenRating(true);
    };

    const { fetchEvents } = useEventStore();

    useEffect(() => {
        fetchEvents();
    }, [page, orderBy]);


    const sortedEvents = [...events].sort((a, b) => {
        switch (sortBy) {
            case "title":
                return a.title.localeCompare(b.title);
            case "location":
                return a.location.localeCompare(b.location);
            case "rating":
                return calcAvg(b.ratings) - calcAvg(a.ratings);
            case "date":
            default:
                return new Date(a.eventDate).getTime() - new Date(b.eventDate).getTime();
        }
    });

    if (loading) return <Typography>Loading events...</Typography>;
    if (error) return <Typography color="error">{error}</Typography>;

    return (
        <>
            <Toolbar />
            <Stack spacing={2} direction="row">
                <Button disabled={page >= 3} color={"inherit"} variant="outlined" onClick={() => addPage(page)} > Next Page</Button>
                <div color={"inherit"} >{page}</div>
                <Button disabled={page <= 0} color={"inherit"} variant="outlined"  onClick={() => minusPage(page)}> PRev Page</Button>
            </Stack>
            <FormControl sx={{ minWidth: 200, mb: 2 }}>
                <InputLabel>Sort By</InputLabel>
                <Select
                    value={sortBy}
                    label="Sort By"
                    onChange={(e) => setSortBy(e.target.value)}
                >
                    <MenuItem value="date">Date</MenuItem>
                    <MenuItem value="title">Title</MenuItem>
                    <MenuItem value="location">Location</MenuItem>
                    <MenuItem value="rating">Average Rating</MenuItem>
                </Select>
            </FormControl>
            <Grid container spacing={{ xs: 1, md: 3 }}>
                {sortedEvents.map((event) => (
                        <Card key={event.id} sx={{ maxWidth: 500, display: "flex", flexDirection: "column" }}>
                            <CardMedia
                                component="img"
                                height={200}
                                image={event.imageUrl}
                                alt={event.title}
                                sx={{ objectFit: "cover" }}
                            />

                            <CardContent sx={{ flexGrow: 1 }}>
                                <Typography gutterBottom variant="h6">
                                    {event.title}
                                </Typography>

                                <Typography variant="body2">
                                    {event.description}
                                </Typography>

                                <Typography variant="body2" sx={{ mt: 1 }}>
                                    {event.location} |{" "}
                                    {new Date(event.eventDate).toLocaleDateString()}
                                </Typography>

                                <Typography variant="body2" sx={{ mt: 1 }}>
                                    Average Rating:{" "}
                                    {calcAvg(event.ratings)}
                                </Typography>
                            </CardContent>

                            <CardActions>
                                <Button
                                    component={Link}
                                    to={`/events/${event.id}`}
                                    variant="contained"
                                    sx={{ backgroundColor: "#170f83", color: "white" }}
                                >
                                    Details
                                </Button>

                                <Button
                                    variant="outlined"
                                    sx={{ ml: 1 }}
                                    onClick={() => handleOpenRating(event.id)}
                                >
                                    Rate
                                </Button>
                            </CardActions>
                        </Card>
                ))}

                {selectedEventId && (
                    <RatingDialog
                        open={openRating}
                        eventId={selectedEventId}
                        onClose={() => setOpenRating(false)}
                        onSubmitted={() => setOpenRating(false)}
                    />
                )}
            </Grid>
        </>
    );
};

export default EventOverview;