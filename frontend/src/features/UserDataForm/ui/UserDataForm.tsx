"use client";
import { useUserDataForm } from "../lib/useUserDataForm";
import { Controller } from "react-hook-form";
import {
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import { DatePicker } from "@mui/x-date-pickers";
import moment from "moment/moment";

export const UserDataForm = () => {
  const { control } = useUserDataForm();

  return (
    <>
      <Controller
        name={"firstname"}
        control={control}
        rules={{ required: true }}
        render={({ field }) => (
          <TextField
            {...field}
            margin="normal"
            required
            fullWidth
            id="firstname"
            label={"Имя"}
            autoComplete="firstname"
          />
        )}
      />
      <Controller
        name={"lastname"}
        control={control}
        rules={{ required: true }}
        render={({ field }) => (
          <TextField
            {...field}
            margin="normal"
            required
            fullWidth
            id="lastname"
            label={"Фамилия"}
            autoComplete="lastname"
          />
        )}
      />
      <Controller
        name={"patronymic"}
        control={control}
        rules={{ required: true }}
        render={({ field }) => (
          <TextField
            {...field}
            margin="normal"
            fullWidth
            id="patronymic"
            label={"Отчество"}
            autoComplete="patronymic"
          />
        )}
      />
      <Controller
        name={"gender"}
        control={control}
        rules={{ required: true }}
        render={({ field }) => (
          <FormControl fullWidth>
            <InputLabel id="gender">Пол</InputLabel>
            <Select
              {...field}
              fullWidth
              id="gender"
              label={"Пол"}
              defaultValue={"Unknown"}
            >
              <MenuItem value={"Male"}>Мужской</MenuItem>
              <MenuItem value={"Female"}>Женский</MenuItem>
              <MenuItem value={"Unknown"}>Не определенно</MenuItem>
            </Select>
          </FormControl>
        )}
      />
      <Controller
        name={"birthday"}
        control={control}
        rules={{ required: true }}
        render={({ field }) => (
          <FormControl fullWidth>
            <DatePicker
              label={"Дата рождения"}
              {...field}
              disableFuture
              minDate={moment().date(1).month(1).year(1900)}
            />
          </FormControl>
        )}
      />
    </>
  );
};
