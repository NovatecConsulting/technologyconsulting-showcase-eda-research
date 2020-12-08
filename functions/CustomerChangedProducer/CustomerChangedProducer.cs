using System;
using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Extensions.Http;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using TCShowcaseEDAResearch.Model;

namespace TCShowcaseEDAResearch
{
  public static class CustomerChangedProducer
  {
    [FunctionName("CustomerChangedProducer")]
    [return: EventHub("EVENT_HUB_NAME", Connection = "CONNECTION_STRING")]
    public static async Task<IActionResult> Run(
        [HttpTrigger(AuthorizationLevel.Anonymous, "put", Route = null)] HttpRequest req,
        ILogger log)
    {
      var requestBody = await new StreamReader(req.Body).ReadToEndAsync();
      var data = JsonConvert.DeserializeObject<Customer>(requestBody);

      log.LogInformation("CustomerChangedProducer function triggered");

      return new OkObjectResult(data);
    }
  }
}
