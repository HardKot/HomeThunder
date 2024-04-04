import {Moment} from "moment/moment";

export interface UserData {
  firstname: string,
  lastname: string,
  patronymic: string,
  gender: string,
  birthday: Moment,

}
