import "reflect-metadata";
import { injectable } from "inversify";
import { IFileUtils } from "../interface";

@injectable()
export class FileUtils implements IFileUtils {
  updateName(oldName: string) {
    let name = "";
    const chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    const extension = oldName.split('.').at(-1);

    for (let i = 0; i < 16; i++) {
      name += chars[Math.floor(Math.random() * chars.length)]; 
    }
    return `${name}.${extension}`;
  }


}
