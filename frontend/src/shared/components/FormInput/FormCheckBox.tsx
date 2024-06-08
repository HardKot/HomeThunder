import Checkbox, { CheckboxProps } from "@mui/material/Checkbox";
import { Control, Controller, FieldValues, Path } from "react-hook-form";

interface FormCheckboxProps<TFieldValues extends FieldValues = FieldValues>
  extends CheckboxProps {
  name: Path<TFieldValues>;
  control: Control<TFieldValues>;
}

export const FormCheckbox = <TFieldValues extends FieldValues = FieldValues>({
  name,
  control,
  ...props
}: FormCheckboxProps<TFieldValues>) => (
  <Controller
    name={name}
    control={control}
    render={({ field }) => <Checkbox {...field} {...props} />}
  />
);
