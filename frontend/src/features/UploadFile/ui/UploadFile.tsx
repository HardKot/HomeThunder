import { useUploadFile } from "../models/useUploadFile"
import { UploadFileProps } from "../models/UploadFileProps"

export const UploadFile = (props: UploadFileProps) => {
  const { onChange } = useUploadFile(props);

  return (
        <input 
          type="file" 
          accept={props.accept}
          onChange={onChange}
          disabled={props.disabled}
        />
  )
}
