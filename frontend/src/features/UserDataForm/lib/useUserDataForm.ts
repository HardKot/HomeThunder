import { useFormContext } from "react-hook-form";
import { UserData } from "@/entities/user";

export const useUserDataForm = () => {
  const { control } = useFormContext<UserData>();

  return { control };
};
