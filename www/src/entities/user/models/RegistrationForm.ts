import {UserData} from "@/entities/user/models/UserData";

export interface RegistrationForm extends UserData{
  email: string,

  password: string,
  passwordConfirmed: string,
}
