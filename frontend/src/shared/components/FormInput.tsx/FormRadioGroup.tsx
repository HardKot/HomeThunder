import { FormControlLabel, Radio, RadioGroup, RadioGroupProps, RadioProps } from "@mui/material";
import { createContext, useContext, FC } from "react";
import { Control, Controller, ControllerRenderProps, FieldValues } from "react-hook-form";


interface FormRadioGroupProps<TFieldValues extends FieldValues = FieldValues> extends RadioGroupProps {
	name: string;
	control: Control<TFieldValues>;
}

interface FormRadioGroupComponent<TFieldValues extends FieldValues = FieldValues> extends FC<FormRadioGroupProps<TFieldValues>> {
	Radio: FC<FormRadioGroupRadioProps>;
}

interface FormRadioGroupRadioProps extends RadioProps {
	label: string;
	value: string;
}




const FormRadioGroup: FormRadioGroupComponent = ({ name, control, children, ...props}) => (
		<Controller
			name={name}
			control={control}
			render={({ field }) => 
				<RadioGroup {...field} {...props}>
					{children}
				</RadioGroup>
			}
			/>
	);


const FormRadioGroupRadio = ({ label, value, ...props }: FormRadioGroupRadioProps) => (
	<FormControlLabel 
		value={value}
		label={label}
		control={<Radio {...props}/>}
	/>
)


FormRadioGroup.Radio = FormRadioGroupRadio;


export default FormRadioGroup;
