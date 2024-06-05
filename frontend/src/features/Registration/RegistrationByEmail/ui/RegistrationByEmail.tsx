import {TextField} from "@mui/material";

import {Controller, useFormContext} from "react-hook-form";
import {RegistrationForm} from "@/entities/user";
import {MVVMProvider} from "@/shared/react-utils/MVVM";


export const RegistrationByEmail = MVVMProvider(
  (props = {}) => ({...useFormContext<RegistrationForm>(), ...props}),
  ({control}) => (<>
    <Controller
      name={"email"}
      control={control}
      render={({field}) => (
        <TextField
          {...field}
          margin="normal"
          fullWidth
          id="patronymic"
          label={"Эл.почта"}
          autoComplete="email"
          required
        />
      )}
    />

    <Controller
      name={"password"}
      control={control}
      rules={{required: true}}
      render={({field}) => (
        <TextField
          {...field}
          margin="normal"
          required
          fullWidth
          id="password"
          type={"password"}
          label={"Пароль"}
        />
      )}
    />
    <Controller
      name={"confirmPassword"}
      control={control}
      rules={{required: true}}
      render={({field}) => (
        <TextField
          {...field}
          margin="normal"
          required
          fullWidth
          id="confirmPassword"
          type={"password"}
          label={"Подтверждение пароля"}
        />
      )}
    />
  </>)
)
