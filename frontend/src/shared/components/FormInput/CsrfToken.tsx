"use client"
import { TYPES } from "@/ioc";
import { useContainer, useInjection } from "inversify-react";
import {Control, Controller } from "react-hook-form";

interface ICsrfToken {
  csrf_token: string 
}

interface CsrfTokenProps {
  control?: Control<ICsrfToken>
}


const CsrfToken = ({ control }: CsrfTokenProps) => {
  const token = useInjection(TYPES.CSRFToken) as string;
  if (!token) return null;

  return (
    <Controller
      render={({field}) => <input type="hidden" {...field}/>}
      name={'csrf_token'}
      defaultValue={token}
      control={control}
    />
  )
};

export default CsrfToken;
