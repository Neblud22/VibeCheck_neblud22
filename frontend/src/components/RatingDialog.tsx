import React, { useEffect, useState } from "react";
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button,
    TextField,
    Rating,
    Stack,
    Typography,
} from "@mui/material";
import { useForm, Controller } from "react-hook-form";
import { fetchRatingsByEvent } from "../services/service.ts";
import type { Rating as RatingType } from "../common/model.ts";
import { useEventStore } from "../store/useEventStore.ts";

interface RatingDialogProps {
    open: boolean;
    eventId: number;
    onClose: () => void;
    onSubmitted: () => void;
}

interface RatingForm {
    stars: number;
    comment: string;
}

const RatingDialog: React.FC<RatingDialogProps> = ({ open, eventId, onClose, onSubmitted}) => {
    const { control, handleSubmit, reset } = useForm<RatingForm>({
        defaultValues: { stars: 0, comment: "" },
    });

    const [ratings, setRatings] = useState<RatingType[]>([]);
    const { addRatingToEvent } = useEventStore();

    // Fetch ratings when dialog opens
    useEffect(() => {
        if (open) {
            fetchRatingsByEvent(eventId)
                .then(setRatings)
                .catch((err) => console.error("Failed to fetch ratings:", err));
        }
    }, [open, eventId]);

    const onSubmit = async (data: RatingForm) => {
        console.log("SENDING:", data);
        try {
            // const saved = await postRating(eventId, data);

            // Update global Zustand store
            addRatingToEvent(eventId, data.stars, data.comment);

            // Update local dialog list
            // setRatings((prev) => [...prev, saved]);

            reset();
            onSubmitted();
        } catch (err) {
            console.error("Failed to submit rating:", err);
        }
    };

    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
            <DialogTitle>Rate Event</DialogTitle>

            <form onSubmit={handleSubmit(onSubmit)}>
                <DialogContent sx={{ display: "flex", flexDirection: "column", gap: 2 }}>
                    {/* Existing Ratings */}
                    {ratings.length > 0 ? (
                        <Stack spacing={2} sx={{ mb: 2 }}>
                            {ratings.map((r) => (
                                <Stack key={r.ratingId} spacing={0.5}>
                                    <Rating value={r.stars} precision={0.5} readOnly />
                                    <Typography variant="body2">{r.comment}</Typography>
                                    <Typography variant="caption" color="textSecondary">
                                        {new Date(r.createdAt).toLocaleString()}
                                    </Typography>
                                </Stack>
                            ))}
                        </Stack>
                    ) : (
                        <Typography>No ratings yet.</Typography>
                    )}

                    {/* New Rating Form */}
                    <Controller
                        name="stars"
                        control={control}
                        rules={{ required: true }}
                        render={({ field }) => (
                            <Rating
                                {...field}
                                value={field.value || 0}
                                precision={0.5}
                                onChange={(_, value) => field.onChange(value)}
                            />
                        )}
                    />


                    <Controller
                        name="comment"
                        control={control}
                        render={({ field }) => (
                            <TextField
                                {...field}
                                label="Comment"
                                multiline
                                rows={4}
                                fullWidth
                            />
                        )}
                    />
                </DialogContent>

                <DialogActions>
                    <Button onClick={onClose}>Cancel</Button>
                    <Button type="submit" variant="contained">
                        Submit
                    </Button>
                </DialogActions>
            </form>
        </Dialog>
    );
};

export default RatingDialog;