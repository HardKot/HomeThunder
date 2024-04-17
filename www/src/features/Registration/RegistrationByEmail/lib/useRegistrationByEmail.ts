import { useFormContext } from "react-hook-form";
import { RegistrationForm } from "@/entities/user";

export const useRegistrationByEmail = () => {
  const { control } = useFormContext<RegistrationForm>();

  return {
    control,
  };
};
