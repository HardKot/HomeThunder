import { useFormContext } from "react-hook-form";
import { RegistrationForm } from "@/entities/user";

export const usePassworddConfirm = () => {
  const { control } = useFormContext<RegistrationForm>();

  return {
    control,
  };
};
