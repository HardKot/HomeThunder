import { TextField } from "@mui/material";

import { Controller } from "react-hook-form";
import { useRegistrationByEmail } from "@/features/Registration/RegistrationByEmail/lib/useRegistrationByEmail";

export const RegistrationByEmail = () => {
  const { control } = useRegistrationByEmail();

  return (
    <>
      <Controller
        name={"email"}
        control={control}
        rules={{ required: true }}
        render={({ field }) => (
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
        rules={{ required: true }}
        render={({ field }) => (
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
        rules={{ required: true }}
        render={({ field }) => (
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
    </>
  );
};
