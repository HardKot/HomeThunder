import { useTranslation } from "next-i18next";
import { useForm } from "react-hook-form";

export const useLoginByEmail = () => {
  const { t } = useTranslation();
  const { control } = useForm();

  return {
    t,
    control,
  };
};
