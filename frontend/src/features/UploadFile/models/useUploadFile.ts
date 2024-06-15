import { ChangeEvent, useCallback, useState } from "react";
import uploadFileAction from "../api/uploadFileAction";
import { useCsrfToken } from "@/ioc/hooks";
import { UploadFileProps } from "./UploadFileProps";

export const useUploadFile = ({ onReject, onSuccess, onLoading }: UploadFileProps) => {
  const crsfToken = useCsrfToken();

  return {
    onChange: useCallback(async ({ target }: ChangeEvent<HTMLInputElement>) => {
      onLoading()
      try {
        const formData = new FormData();
        for (const file of target.files ?? []) {
          formData.append(file.name, file);
        }
      
        formData.set("csrf_token", crsfToken);
        
        const result = await uploadFileAction(formData);
        const value = result
          .map(([_, newUrl]) => newUrl)
          .filter(newUrl => newUrl !== "error")
      
        onSuccess(value);
        const fileWithError = 
          result
            .filter(([_, newUrl]) => newUrl !== "error")
            .map(([fileName, _]) => fileName)

        if (fileWithError.length) onReject(fileWithError);
      } catch (error) {
        let message = "Неизвестная ошибка при загрузке изображения";
        if (error instanceof Error) message = error.message;
        console.error(message);
        onReject("any");
      }
      
    }, [crsfToken])
  }
}
