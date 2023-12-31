import { Blog } from "./blog";
import { PlaceImage } from "./place-image";
import { PlaceRating } from "./place-rating";
import { User } from "./user";

export class Place {
  id: number;
  name: string;
  street: string;
  zipcode: string;
  description: string;
  imageUrl: string;
  enabled: boolean;
  images: PlaceImage[];
  ratings: PlaceRating[];
  blogs: Blog[];
  user: User;

  constructor(id = 0, name = '', street = '', zipcode = '', description = '', imageUrl = '', enabled = false, images = [], ratings = [], blogs = [], user = new User()) {
    this.id = id;
    this.name = name;
    this.street = street;
    this.zipcode = zipcode;
    this.description = description;
    this.imageUrl = imageUrl;
    this.enabled = enabled;
    this.images = images;
    this.ratings = ratings;
    this.blogs = blogs;
    this.user = user;
  }
}
