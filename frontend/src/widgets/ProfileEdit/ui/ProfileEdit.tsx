import {FormProvider} from "react-hook-form";
import {UserDataForm} from "@/features/UserDataForm";
import {Button, Grid} from "@mui/material";
import {useEditProfile} from "@/widgets/ProfileEdit/libs/useEditProfile";

export const ProfileEdit = () => {
  const { formMethod, onSubmit } = useEditProfile()

  return (
    <Grid component={"form"} noValidate onSubmit={onSubmit}>
      <FormProvider {...formMethod}>
        <UserDataForm />
        <Button
          type="submit"
          fullWidth
          variant="contained"
          sx={{ mt: 3, mb: 2 }}
        >
          Сохранить
        </Button>
      </FormProvider>
    </Grid>
  )
}
