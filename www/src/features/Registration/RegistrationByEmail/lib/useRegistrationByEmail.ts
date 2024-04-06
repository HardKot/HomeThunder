import { useTranslation } from "next-i18next";
import { useFormContext } from "react-hook-form";
import { RegistrationForm } from "@/entities/user";

export const useRegistrationByEmail = () => {
  const { t } = useTranslation();
  const { control } = useFormContext<RegistrationForm>();

  return {
    t,
    control,
  };
};
