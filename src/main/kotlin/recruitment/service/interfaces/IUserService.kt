package recruitment.service.interfaces

import recruitment.forms.UserForm

interface IUserService {
     fun checkUserAvailability(userForm: UserForm): String?
}