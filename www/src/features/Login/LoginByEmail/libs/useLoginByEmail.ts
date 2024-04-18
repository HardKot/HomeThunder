import { useForm } from "react-hook-form";

export const useLoginByEmail = () => {
  const { control } = useForm();
  
  return {
    control,
  };
};
