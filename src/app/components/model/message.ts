import {Like} from "./like";
import {Tag} from "./tag";

export interface Message {
    id: number;
    header: string;
    text: string;
    likes: Like[]
    tag: { tag: Tag }[]
}