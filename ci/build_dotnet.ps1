$env:BASYX_REPO = "$(Get-Location)..\sdks\dotnet\basyx-packages"
Get-ChildItem $env:USERPROFILE\.nuget -Directory -Filter basyx* -Recurse | Remove-Item -force -Recurse
dotnet build ..\sdks\dotnet\basyx-core\BaSyx.Core.sln
dotnet build ..\sdks\dotnet\basyx-components\BaSyx.AAS.Client.Http\BaSyx.AAS.Client.Http.csproj
dotnet build ..\sdks\dotnet\basyx-components\BaSyx.Components.sln


