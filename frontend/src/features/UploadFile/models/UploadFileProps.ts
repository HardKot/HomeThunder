import { FC, ReactNode } from "react";
import { Control, FieldValues } from "react-hook-form"

export interface UploadFileProps {
  accept: string;
  onSuccess: (links: string[]) => void;
  onReject: (names: string[] | "any") => void;
  onLoading: () => void;
}
