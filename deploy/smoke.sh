#!/bin/bash
: '//This is script for CoffeeOutlet test api
'
set -o errexit                     
HOST=${1:-'localhost'}
PORT=${2:-'8082'}
REJECT_ON_CODES=${3:-(400 500)}
EXIT_CODE=11
token=""
#Method to invoke CoffeeOutlet authentication endpoints
callAuth() {
    local host=${1:-$HOST}
    local port=${2:-$PORT}
    local url="http://$host:$port/oauth/token?grant_type=password&username=vikas_555&password=vikas@123"
	local header="Authorization: Basic Y2xpZW50OmNsaWVudDEyMw=="
    echo "Calling Authentication Endpoint: $url"
	echo "curl --location --request POST  $url --header $header"
    local response=$(curl --location --request POST  "$url" --header "$header")
	
     if [ -z "$response" ];
     then
        echo "No Response could be obtained from Authentication endpoint. Exiting.."
        exit "$EXIT_CODE"
    else
        echo "Response from callEndpoint : $response"
    fi

token=$( jq -r ".access_token" <<<"$response" )


}

#Method to call CoffeeOutletList API Endpoint: REST

callEndpoint_1(){
    local url="${1}"
    local header="${2}"
    local request="${3}"
    #Exit if url, header or request is missing.
    if [ -z "$url" ]; then
        echo "URL is missing!!"
        echo "URL: $url"
        exit "$EXIT_CODE"
    elif [ -z "$header" ]; then
        echo "Header is missing!!"
        echo "header: $header"
        exit "$EXIT_CODE"
    elif [ -z "$request" ]; then
        echo "Request is missing!!"
        echo "Request: $request"
        exit "$EXIT_CODE"
    fi
    echo "Calling CoffeeOutlet list Endpoint: $url"
	echo "Header: $header"
    local status=$(curl --location --request   GET \
                --data-raw "${request}"  \
                --header "${header}" \
                 --write-out %{http_code} --silent --output /dev/null "$url")
    echo "Response Code of CoffeeOutletList Endpoint: $status"
    [[  ${REJECT_ON_CODES[*]}  =~  $status  ]] || [[ $status == 000 ]]
}

#Method to call ListOrderByCustomer API Endpoint: REST

callEndpoint_2(){
    local url="${1}"
    local header="${2}"
    local request="${3}"
    #Exit if url, header or request is missing.
    if [ -z "$url" ]; then
        echo "URL is missing!!"
        echo "URL: $url"
        exit "$EXIT_CODE"
    elif [ -z "$header" ]; then
        echo "Header is missing!!"
        echo "header: $header"
        exit "$EXIT_CODE"
    elif [ -z "$request" ]; then
        echo "Request is missing!!"
        echo "Request: $request"
        exit "$EXIT_CODE"
    fi
    echo "Calling ListOrderByCustomer Endpoint: $url"
    local status=$(curl --location --request   GET \
                --data-raw "${request}"  \
                --header "${header}" \
                 --write-out %{http_code} --silent --output /dev/null "$url")
    echo "Response Code of ListOrderByCustomer Endpoint: $status"
    [[  ${REJECT_ON_CODES[*]}  =~  $status  ]] || [[ $status == 000 ]]
}
callRestService(){
    local url="http://$HOST:$PORT/outlet/list/Punjab"
    local authorization="Authorization: Bearer"
    local header="$authorization$token"
    callEndpoint_1 "${url}" "${header}" '<JSON>'

local url="http://$HOST:$PORT/customer/order/3"
    local header="$authorization$token"
    callEndpoint_2 "${url}" "${header}" '<JSON>'
}
#calling methods to invoke CoffeeOutlet endpoints
callAuth
callRestService && echo "Unable to establish connection with CoffeeOutlet Rest endpoint." && exit "$EXIT_CODE"









