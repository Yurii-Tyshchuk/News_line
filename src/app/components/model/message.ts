import {Like} from "./like";

export interface Message {
    id: number;
    header: string;
    text: string;
    likes: Like[]
}