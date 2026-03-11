import api from "../api/api-client.ts";
import type { Artist, Event, Rating } from "../common/model";

export const fetchAllArtists = async (): Promise<Artist[]> => {
    const response = await api.get<Artist[]>("/artists");
    return response.data;
};

export const fetchArtistById = async (artistId: number): Promise<Artist> => {
    const response = await api.get<Artist>(`/artists/${artistId}`);
    return response.data;
};

type PagedEvents = { // not bypassable
    content: Event[];
};

export const fetchAllEvents = async (path: string = ""): Promise<Event[]> => {
    const response = await api.get<PagedEvents>(`/events${path}`);
    console.log("EVENT RESPONSE:", response.data.content);
    return response.data.content;
};

export const fetchEventById = async (eventId: number): Promise<Event> => {
    const response = await api.get<Event>(`/events/${eventId}`);
    console.log(response)
    return response.data;
};

export const fetchRatingsByEvent = async (eventId: number): Promise<Rating[]> => {
    const response = await api.get<Rating[]>(`/ratings/event/${eventId}`);
    return response.data;
};

export const postRating = async (
    eventId: number,
    rating: { stars: number; comment: string }
): Promise<Rating> => {
    return await api.post(`/ratings/${eventId}`, rating);
};