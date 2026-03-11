import React, { useEffect, useState } from "react";
import type { Event } from "../common/model";
import { useParams, useNavigate } from "react-router-dom";
import { Stack, Typography, Button, CardMedia } from "@mui/material";
import { useEventStore } from "../store/useEventStore";

const EventDetail: React.FC = () => {
    const { eventId } = useParams<{ eventId: string }>();
    const [event, setEvent] = useState<Event | null>(null);
    const navigate = useNavigate();
    const { calcAvg } = useEventStore();
    const { fetchEventById, loading, error } = useEventStore();


    useEffect(() => {
        if (eventId) {
            fetchEventById(Number(eventId)).then(e => setEvent(e));
        }
    }, [eventId]);

    if (loading) return <Typography>Loading artists...</Typography>;
    if (error) return <Typography color="error">{error}</Typography>;
    if (!event) return null;

    return (
        <Stack spacing={2} sx={{ mt: 10, p: 2 }}>
            <CardMedia
                component="img"
                height={300}
                image={event.imageUrl}
                alt={event.title}
                sx={{ objectFit: "cover" }}
            />

            <Typography variant="h4">{event.title}</Typography>
            <Typography>{event.description}</Typography>

            <Typography>
                Location: {event.location} | Date:{" "}
                {new Date(event.eventDate).toLocaleDateString()}
            </Typography>

            <Typography>
                Average Rating:{" "}
                {event.ratings.length > 0
                    ? calcAvg(event.ratings).toFixed(1)
                    : "N/A"}
            </Typography>

            <Stack direction="row" spacing={2} alignItems="center">
                <Typography variant="h6">Artists:</Typography>

                <Stack direction="row" spacing={2} flexWrap="wrap">
                    {event.artists?.map((artist) => (
                        <Typography key={artist.id}>
                            {artist.firstName} {artist.lastName}
                        </Typography>
                    ))}
                </Stack>
            </Stack>

            <Button variant="contained" sx={{ mt: 2 }} onClick={() => navigate(-1)}>
                Back
            </Button>
        </Stack>
    );
};

export default EventDetail;