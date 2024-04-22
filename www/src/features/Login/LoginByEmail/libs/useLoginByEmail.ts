import {useForm, useFormContext} from "react-hook-form";

export const useLoginByEmail = () => {
  const { control } = useFormContext();

  return {
    control,
  };
};
