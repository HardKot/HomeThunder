"use client"
import { Button, MenuItem, TextField } from "@mui/material";

import { FormContainer, SelectElement, TextFieldElement } from "react-hook-form-mui";
import { DatePickerElement } from "react-hook-form-mui/date-pickers"
import { FormUtils } from "@/shared/libs/FormUtils";
import Lodash from "lodash";
import moment from "moment";
import CsrfToken from "@/shared/components/FormInput/CsrfToken";


export const RegistrationByEmail = () => (
  <FormContainer
    onSuccess={Lodash.flow(FormUtils.fromObject, (a) => console.log([...a.entries()]))}
  >
    <TextFieldElement
        name={"firstname"}
            margin="normal"
            required
            fullWidth
            label={"Имя"}
            autoComplete="firstname"
      />
      <TextFieldElement
        name={"lastname"}
            margin="normal"
            required
            fullWidth
            label={"Фамилия"}
            autoComplete="lastname"
      />
      <TextFieldElement
        name={"patronymic"}
            margin="normal"
            fullWidth
            label={"Отчество"}
            autoComplete="patronymic"
      />
      <SelectElement
        name={"gender"}
        label="Пол"
        fullWidth
        defaultValue={"Unknown"}
        margin="normal"
        options={[
          { id: "Male", label: "Мужской" },
          { id: "Female", label: "Женский" },
          { id: "Unknown", label: "Не определенно" }
        ]}
        />
      
      <DatePickerElement
        name={"birthday"}
        label={"Дата рождения"}
        disableFuture
        
        minDate={moment().date(1).month(1).year(1900)}
      />
      <TextFieldElement
        name={"email"}
        margin="normal"
        fullWidth
        label={"Эл.почта"}
        autoComplete="email"
        required
      />

      <TextFieldElement
        name={"password"}
        margin="normal"
        required
        fullWidth
        type={"password"}
        label={"Пароль"}
      />
      <TextFieldElement
        name={"confirmPassword"}
        margin="normal"
        required
        fullWidth
        type={"password"}
        label={"Подтверждение пароля"}
      />
      <CsrfToken />
      <Button type={"submit"} variant={"contained"} fullWidth>
          Зарегестрироваться
      </Button>
    </FormContainer>
)  
