import {CssBaseline, Grid, Link, Paper, Typography} from "@mui/material";
import { createTheme, ThemeProvider } from '@mui/material/styles';
import {useTranslation} from "next-i18next";
import {Registration} from "@/widgets/Registration";

export const RegistrationPage = () => {
  const defaultTheme = createTheme();
  const { t } = useTranslation()



  return (
    <ThemeProvider
      theme={defaultTheme}
    >
      <Grid
        container component="main" sx={{ height: '100vh' }}
      >
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: 'url(/background.jpeg)',
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Registration />
          <Grid container>
            <Grid item>
              <Link href="/login">
                {t("Войти")}
              </Link>
            </Grid>
          </Grid>
        </Grid>

      </Grid>
    </ThemeProvider>
  )
}
