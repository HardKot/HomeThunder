import Checkbox, { CheckboxProps } from '@mui/material/Checkbox';
import { Control, Controller, FieldName, FieldPath, FieldValues } from "react-hook-form";

interface FormCheckboxProps<TFieldValues extends FieldValues = FieldValues> extends CheckboxProps {
	name: string;
	control: Control<FieldValues>;
}

export const FormCheckbox = <TFieldValues extends FieldValues = FieldValues,>({ name, control, ...props }: FormCheckboxProps) => 
	<Controller
		name={name}
		control={control}
		render={({ field }) => <Checkbox {...field} {...props}/>}
		/>
