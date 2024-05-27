import { Checkbox, FormControlLabel, TextField } from "@mui/material";
import { Controller } from "react-hook-form";
import { useLoginByEmail } from "@/features/Login/LoginByEmail/libs/useLoginByEmail";

export interface LoginProps {}

export const LoginByEmail = ({}: LoginProps) => {
  const {  control } = useLoginByEmail();

  return (
    <>
      <Controller
        name={"email"}
        control={control}
        rules={{ required: true }}
        render={({ field, fieldState,  }) => (
          <TextField
            {...field}
            error={!!fieldState.error?.message}
            helperText={fieldState.error?.message}
            margin="normal"
            required
            fullWidth
            id="email"
            label={"Эл.почта"}
            autoComplete="email"
            autoFocus
          />
        )}
      />
      <Controller
        name={"password"}
        control={control}
        rules={{ required: true }}
        render={({ field, fieldState }) => (
          <TextField
            {...field}
            error={!!fieldState.error?.message}
            margin="normal"
            required
            fullWidth
            id="password"
            type={"password"}
            label={"Пароль"}
            autoComplete="password"
            autoFocus
          />
        )}
      />
      <Controller
        name={"rememberMe"}
        control={control}
        render={({ field }) => (
          <FormControlLabel
            control={<Checkbox {...field} color="primary" />}
            label={"Запомнить меня"}
          />
        )}
      />
    </>
  );
};
