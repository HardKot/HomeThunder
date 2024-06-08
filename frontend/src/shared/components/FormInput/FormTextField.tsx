import { TextField, TextFieldProps, TextFieldVariants } from "@mui/material";
import { Control, Controller, FieldValues, Path } from "react-hook-form";

type FormTextFieldProps<
  TFieldValues extends FieldValues = FieldValues,
  TFVariant extends TextFieldVariants = TextFieldVariants,
> = {
  name: Path<TFieldValues>;
  control: Control<TFieldValues>;
} & TextFieldProps<TFVariant>;

export const FormTextFiel = <
  TFieldValues extends FieldValues = FieldValues,
  TFVariant extends TextFieldVariants = TextFieldVariants,
>({
  name,
  control,
  ...props
}: FormTextFieldProps<TFieldValues, TFVariant>) => (
  <Controller
    name={name}
    control={control}
    render={({ field }) => <TextField {...field} {...props} />}
  />
);
