import { RadioGroup, RadioGroupProps } from "@mui/material";
import { createContext, useContext } from "react";
import { Control, Controller, ControllerRenderProps, FieldValues } from "react-hook-form";

interface FormRadioGroupProps<TFieldValues extends FieldValues = FieldValues> extends RadioGroupProps {
	name: string;
	control: Control<FieldValues>;
}


const FormRadioContext = createContext<ControllerRenderProps<any, any>| null>(null);
const useFormRadioContext = () => {
	const context = useContext(FormRadioContext);
	if (!context) throw new Error("Context not found");
	return context;
}

function FormRadioGroup({ name, control, children, ...props}: FormRadioGroupProps) {
	return (
		<Controller
			name={name}
			control={control}
			render={({ field }) => 
				<RadioGroup {...field} {...props}>
					<FormRadioContext.Provider value={field}>
						{children}
					</FormRadioContext.Provider>
				</RadioGroup>
			}
			/>
	);
}

FormRadioGroup.Radio = (...props) => {
	const field = useFormRadioContext();
	
	return (
		<FormCo
	)
}
