import empty from 'is-empty'

export default {
    fullName(profile) {
        let firstName, lastName, name;

        if (typeof profile === 'object') {
            ({firstName, lastName, name} = profile)
        } else {
            [firstName, lastName, name] = arguments
        }

        return firstName && lastName
            ? firstName + ' ' + lastName
            : (name ? name : 'User Name')
    }
}
