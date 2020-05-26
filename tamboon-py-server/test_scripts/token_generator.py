import omise


def create_token():
    omise.api_public = "pkey_test_5jy4os10d58ajewqsb4"

    token = omise.Token.create(
        name="Somchai Prasert",
        number="4242424242424242",
        expiration_month=10,
        expiration_year=2022,
        city="Bangkok",
        postal_code="10320",
        security_code=123,
    )
    return token

if __name__ == '__main__':
     print create_token()

