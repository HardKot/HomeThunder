'use client'

import { useRegistration } from "@/widgets/Registration/libs/useRegistration";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import { UserDataForm } from "@/features/UserDataForm";
import { RegistrationByEmail } from "@/features/Registration/RegistrationByEmail";
import {Controller, FormProvider, useFormContext} from "react-hook-form";
import {MVVMProvider} from "@/shared/react-utils/MVVM";
import {RegistrationForm} from "@/entities/user";
import {TextField} from "@mui/material";


export const Registration = MVVMProvider(() => ({}),({}) => (
    <Box
      sx={{
        my: 8,
        mx: 4,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Typography component="h1" variant="h5">
        Регистрация
      </Typography>
      <Grid component={"form"} noValidate onSubmit={onSubmit}>
        <Grid container>
          <FormProvider {...formMethod}>
            <UserDataForm />
            <RegistrationByEmail />
          </FormProvider>
        </Grid>
        <Button
          type="submit"
          fullWidth
          variant="contained"
          sx={{ mt: 3, mb: 2 }}
        >
          Зарегистрироваться
        </Button>
      </Grid>
    </Box>
  ))
