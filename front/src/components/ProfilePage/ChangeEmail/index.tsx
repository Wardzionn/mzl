import CardContent from '@mui/material/CardContent';
import Card from '@mui/material/Card';
import { Button, TextField } from '@mui/material';
import { useGetSelfInfoQuery } from '../../../features/api/apiSlice';
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from 'react-hook-form';
import * as Yup from 'yup';

interface EmailChangeSchema {
  email: string;
}

const emailChangeSchema = Yup.object().shape({
  email: Yup.string().required('E-mail jest wymagany').email('Niepoprawny format e-mail')
});

const ChangeEmail = () => {
  const { data: accountInfo } = useGetSelfInfoQuery();

  const {
    register,
    handleSubmit,
    formState: { errors }
  } = useForm<EmailChangeSchema>({
    resolver: yupResolver(emailChangeSchema)
  });

  return (
    <Card>
      <CardContent>
        <form onSubmit={handleSubmit(() => {})}>
          <p>Zmiana e-mail</p>
          <div className="mt-3">
            <TextField
              {...register('email')}
              label="* email"
              size="small"
              fullWidth
              defaultValue={accountInfo?.payload?.email ?? ''}
              error={!!errors.email}
              helperText={errors.email?.message}
            />
          </div>

          <div className="mt-3 text-center">
            <Button variant="contained" type="submit">
              Zmień e-mail
            </Button>
          </div>
        </form>
      </CardContent>
    </Card>
  );
};

export default ChangeEmail;
