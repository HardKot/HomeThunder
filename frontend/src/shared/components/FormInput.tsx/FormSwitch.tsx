import { Switch, SwitchProps } from '@mui/material';
import { Control, Controller, FieldValues, Path } from "react-hook-form";

interface FormSwitchProps<TFieldValues extends FieldValues = FieldValues> extends SwitchProps {
	name: Path<TFieldValues>;
	control: Control<TFieldValues>;
}

export const FormSwitch = <TFieldValues extends FieldValues = FieldValues,>({ name, control, ...props }: FormSwitchProps<TFieldValues>) =>( 
	<Controller
		name={name}
		control={control}
		render={({ field }) => <Switch {...field} {...props}/>}
		/>
)
		
