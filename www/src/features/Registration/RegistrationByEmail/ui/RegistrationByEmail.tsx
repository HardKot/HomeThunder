import {
  TextField
} from "@mui/material";

import {Controller} from "react-hook-form";
import {useRegistrationByEmail} from "@/features/Registration/RegistrationByEmail/lib/useRegistrationByEmail";


export const RegistrationByEmail = () => {
  const {t, control} = useRegistrationByEmail();

  return (
    <>
      <Controller
        name={'email'}
        control={control}
        rules={{required: true}}
        render={({field}) =>
          <TextField
            {...field}
            margin="normal"
            fullWidth
            id="patronymic"
            label={t('Отчество')}
            autoComplete="patronymic"
          />}
      />

      <Controller
        name={'password'}
        control={control}
        rules={{required: true}}
        render={({field}) => <TextField
          {...field}
          margin="normal"
          required
          fullWidth
          id="password"
          type={"password"}
          label={t('Пароль')}
          autoFocus
        />}
      />
      <Controller
        name={'passwordConfirmed'}
        control={control}
        rules={{required: true}}
        render={({field}) => <TextField
          {...field}
          margin="normal"
          required
          fullWidth
          id="passwordConfirmed"
          type={"password"}
          label={t('Подтверждение пароля')}
          autoFocus
        />}
      />
    </>
  )
}
