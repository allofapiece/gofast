<template>
    <Auth>
        sign up
        <ValidationObserver v-slot="{ invalid }" ref="observer">
            <v-form
                    ref="form"
                    v-model="invalid"
                    @submit.prevent="submit"
            >
                <template v-if="isCompany">
                    <ValidationProvider
                            name="companyName"
                            ref="companyName"
                            rules="required"
                    >
                        <v-text-field
                                v-model="name"
                                prepend-icon="person"
                                slot-scope="{ errors }"
                                :error-messages="errors"
                                label="Company Name"
                                required
                                type="text"
                        ></v-text-field>
                    </ValidationProvider>
                </template>
                <template v-else>
                    <ValidationProvider
                            name="firstName"
                            ref="firstName"
                            rules="required"
                    >
                        <v-text-field
                                v-model="firstName"
                                prepend-icon="person"
                                slot-scope="{ errors }"
                                :error-messages="errors"
                                label="First Name"
                                required
                                type="text"
                        ></v-text-field>
                    </ValidationProvider>
                    <ValidationProvider
                            name="lastName"
                            ref="lastName"
                            rules="required"
                    >
                        <v-text-field
                                v-model="lastName"
                                prepend-icon="person"
                                slot-scope="{ errors }"
                                :error-messages="errors"
                                label="Last Name"
                                required
                                type="text"
                        ></v-text-field>
                    </ValidationProvider>
                </template>
                <ValidationProvider
                        name="email"
                        ref="email"
                        rules="required|email|max:320"
                >
                    <v-text-field
                            v-model="email"
                            prepend-icon="email"
                            slot-scope="{ errors }"
                            :error-messages="errors"
                            label="Email"
                            required
                            type="email"
                    ></v-text-field>
                </ValidationProvider>
                <ValidationProvider
                        name="password"
                        ref="password"
                        rules="required|min:4|max:18|lower:1"
                >
                    <v-text-field
                            v-model="password"
                            label="Password"
                            required
                            type="password"
                            prepend-icon="lock"
                            slot-scope="{ errors }"
                            :error-messages="errors"
                    ></v-text-field>
                </ValidationProvider>
                <ValidationProvider
                        mode="aggressive"
                        name="confirmed password"
                        ref="confirmedPassword"
                        rules="required|confirmed:password"
                >
                    <v-text-field
                            v-model="confirmedPassword"
                            label="Confirm Password"
                            required
                            type="password"
                            prepend-icon="lock"
                            slot-scope="{ errors }"
                            :error-messages="errors"
                    ></v-text-field>
                </ValidationProvider>
                <v-switch v-model="isCompany" label="Are you company?"></v-switch>
                <v-btn
                        :disabled="invalid"
                        color="primary"
                        class="mr-4"
                        type="submit"
                >
                    Sign Up
                </v-btn>
            </v-form>
        </ValidationObserver>
    </Auth>
</template>

<script>
    import Auth from "./Auth.vue";
    import authService from 'service/AuthService'
    import validationHelper from 'validate/helper'

    export default {
        components: {Auth},
        data() {
            return {
                invalid: true,
                firstName: '',
                lastName: '',
                name: '',
                email: '',
                password: '',
                confirmedPassword: '',
                isCompany: false
            }
        },
        methods: {
            submit() {
                let router = this.$router

                authService.signup({
                    'email': this.email,
                    'firstName': this.firstName,
                    'lastName': this.lastName,
                    'password': this.password,
                    'confirmedPassword': this.confirmedPassword,
                    'isCompany': this.isCompany
                }).then((result) => {
                    if (result) {
                        router.push('/signin')
                    }
                }).catch(result => {
                    if (validationHelper.isValidationError(result.response)) {
                        this.showValidationErrors(result.response.data)
                    }
                })
            },
            showValidationErrors(errors) {
                console.log(validationHelper.mapFieldErrors(errors))
                this.$refs.observer.setErrors(validationHelper.mapFieldErrors(errors))
            },
        }
    }
</script>
