export type Artist = {
    id: number;
    firstName: string;
    lastName: string;
    description: string;
    imageUrl: string;
};
export type Event = {
    id: number;
    title: string;
    location: string;
    eventDate: string;
    imageUrl: string;
    description: string;
    artists: Artist[];
    ratings: Rating[];
};
export type Rating = {
    ratingId: number;
    stars: number;
    comment: string;
    createdAt: string;
};