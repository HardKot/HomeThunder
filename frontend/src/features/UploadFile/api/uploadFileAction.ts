"use server"

import { TYPES } from "@/ioc";
import { bindServerDependencies } from "@/ioc/inversify.serverContainer";
import { IFileStorage } from "@/shared/interface";

async function uploadFileAction(
  fileStorage: IFileStorage,
  fileForm: FormData,
) {
  const resultForm: [string, string][] = [];
  const promiseUpload = [];
  for (const file of fileForm.values()) {
    if (!(file instanceof File)) continue;
    const buffer = Buffer.from(await file.arrayBuffer());


    promiseUpload.push(   
      fileStorage.uploadInTemp(file.name, buffer)
        .then(newFileName => resultForm.push([file.name, newFileName]))
        .catch(error => resultForm.push([file.name, "error"]))
                      ) 
  }
  await Promise.all(promiseUpload);
  
  return resultForm;
}



export default bindServerDependencies([TYPES.FileStorage], uploadFileAction) as (fileForm: FormData) => Promise<[string, string | "error"][]>
