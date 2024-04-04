import {Avatar, Box, Button, Checkbox, FormControlLabel, TextField, Typography} from "@mui/material";
import { useTranslation } from 'next-i18next'
import {Controller, useForm } from "react-hook-form";

export interface LoginProps {
  footer?: JSX.Element;
}

export const Login = ({ footer }: LoginProps) => {
  const { t } = useTranslation()
  const { handleSubmit, control, reset } = useForm()


  return (
      <Box
        sx={{
            my: 8,
            mx: 4,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>

        </Avatar>
        <Typography component="h1" variant="h5">{t('Вход')}</Typography>
        <Box component={"form"} noValidate sx={{ mt: 1 }}>
          <Controller
            name={'email'}
            control={control}
            rules={{ required: true }}
            render={({ field }) => <TextField
              {...field}
              margin="normal"
              required
              fullWidth
              id="email"
              label={t('Эл.почта')}
              autoComplete="email"
              autoFocus
            />}
          />
          <Controller
            name={'password'}
            control={control}
            rules={{ required: true }}
            render={({ field }) => <TextField
              {...field}
              margin="normal"
              required
              fullWidth
              id="password"
              type={"password"}
              label={t('Пароль')}
              autoComplete="password"
              autoFocus
            />}
          />
          <Controller
            name={'rememberMe'}
            control={control}
            rules={{ required: true }}
            render={({ field }) =>
              <FormControlLabel
                control={<Checkbox {...field} color="primary" />}
                label={t('Запомнить меня')}
              />
              }
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            {t('Войти')}
          </Button>
          {footer}
        </Box>
      </Box>
    )
}
