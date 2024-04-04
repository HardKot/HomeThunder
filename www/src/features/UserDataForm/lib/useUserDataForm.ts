import {useFormContext} from "react-hook-form";
import {useTranslation} from "next-i18next";
import {UserData} from "@/entities/user";

export const useUserDataForm = () => {
  const { control } = useFormContext<UserData>()
  const {t} = useTranslation()

  return { control, t }
}
