import { useTranslation } from "next-i18next";

export const useRegistrationPage = () => {
  const { t } = useTranslation();

  return {
    t,
  };
};
