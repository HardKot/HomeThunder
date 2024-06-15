import { Control, Controller, FieldValues } from "react-hook-form"

interface UploadFileProps<TFieldValues extends FieldValues = FieldValues>{
  control?: Control<TFieldValues>
  name: string
  accept: string
}

export const UploadFile = ({ name, control, accept }: UploadFileProps) => {
      

  return (
    <Controller
      name={name}
      control={control}
      render={({ field }) => (
        <input 
          type="file" 
          accept={accept}
          onChange={(event) => { 
            field.onChange(event)
          }}
          />
      )}
      />
  )
}
