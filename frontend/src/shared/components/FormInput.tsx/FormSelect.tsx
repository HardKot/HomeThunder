import { MenuItem, MenuItemProps, Select, SelectProps } from '@mui/material';
import { FC } from 'react';
import { Control, Controller, FieldValues, Path } from "react-hook-form";

type FormSelectProps<TFieldValues extends FieldValues = FieldValues, Value = unknown> = SelectProps<Value> & {
	name: Path<TFieldValues>;
	control: Control<TFieldValues>;
}

type FormSelectItemProps = MenuItemProps & {

}

type FormSelectComponent<TFieldValues extends FieldValues = FieldValues, Value = unknown> = FC<FormSelectProps<TFieldValues, Value>> & {
	Item: FC<FormSelectItemProps>
}

const FormSelect: FormSelectComponent = ({ name, control, ...props }) => ( 
	<Controller
		name={name}
		control={control}
		render={({ field }) => <Select {...field} {...props}/>}
		/>
)

const FormSelectItem = ({ ...props }: FormSelectItemProps) => <MenuItem {...props} />


FormSelect.Item = FormSelectItem

export default FormSelect
