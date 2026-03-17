import { create } from "zustand";
import type {Artist, Event, Rating} from "../common/model";
import {
    fetchAllArtists,
    fetchAllEvents,
    fetchArtistById, fetchEventById,
    fetchRatingsByEvent,
    postRating
} from "../services/service.ts";

interface EventStore {
    page: number,
    addPage: (pageNum:number) => void,
    minusPage: (pageNum:number) => void,

    sortOrder: string,
    orderBy: string,
    size: number,
    setSortOrder: (order: string) => void,

    loading: boolean;
    error: string | null;
    events: Event[];
    artists: Artist[];
    calcAvg: (ratings: Rating[]) => number;
    fetchEvents: () => Promise<void>;
    fetchAllArtists: () => Promise<void>;
    fetchArtistById: (id: number) => Promise<Artist | null>;
    fetchEventById: (id: number) => Promise<Event | null>;
    fetchRatingsByEvent: (eventId: number) => Promise<Rating[]>;
    addRatingToEvent: (eventId: number, stars: number, comment: string) => Promise<void>;
}

export const useEventStore = create<EventStore>((set, get) => ({
    page: 0,
    size: 2,
    orderBy: "name",
    sortOrder: "asc",
    addPage: () => set(state => ({ page: state.page + 1 })),
    minusPage: () => set(state => ({ page: Math.max(0, state.page - 1) })),
    setSortOrder: (order: string) => set({ orderBy: order }),

    loading: false,
    error: null,
    events: [],
    artists: [],
    calcAvg: (ratings) => {
        let total = 0;
        ratings.forEach(r => {
            total += r.stars;
        });
        return parseFloat((total / ratings.length).toFixed(2));
    },
    fetchEvents: async() => {
        try {
            const events = await fetchAllEvents();
            set({events: events})
            console.log(events)
        } catch (err) {
            console.error(err);
        }
    },
    fetchAllArtists: async () => {
        set({ loading: true, error: null });
        try {
            const artists = await fetchAllArtists();
            set({ artists });
        } catch (err: any) {
            set({ error: err.message });
        } finally {
            set({ loading: false });
        }
    },

    fetchArtistById: async (id: number) => {
        set({ loading: true, error: null });
        try {
            return await fetchArtistById(id);
        } catch (err: any) {
            set({ error: err.message });
            return null;
        } finally {
            set({ loading: false });
        }
    },

    fetchEventById: async (id: number) => {
        set({ loading: true, error: null });
        try {
            return await fetchEventById(id);
        } catch (err: any) {
            set({ error: err.message });
            return null;
        } finally {
            set({ loading: false });
        }
    },

    fetchRatingsByEvent: async (eventId: number) => {
        set({ loading: true, error: null });
        try {
            return await fetchRatingsByEvent(eventId);
        } catch (err: any) {
            set({ error: err.message });
            return [];
        } finally {
            set({ loading: false });
        }
    },
    addRatingToEvent: async (eventId: number, stars: number, comment: string) => {
        set({ loading: true, error: null });
        try {
            await postRating(eventId, { stars, comment });
            const updatedRating = await fetchRatingsByEvent(eventId);
            set({ ratings: updatedRating });
            const avgRating = get().calcAvg(updatedRating);
            set({ event: (prev) => prev ? { ...prev, avgRating } : null });
        } catch (err: any) {
            set({ error: err.message });
        } finally {
            set({ loading: false });
        }
    },
}));